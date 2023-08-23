<?php

namespace App\Form;

use App\Entity\Scripts;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class Scripts1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('id',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('ref',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('etat',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('refComposant',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('rgSgSiCluster',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('script',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('codeErreur',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('criticite',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('description',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('instructions',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('monitoredBy',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('refService',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
           
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Scripts::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
