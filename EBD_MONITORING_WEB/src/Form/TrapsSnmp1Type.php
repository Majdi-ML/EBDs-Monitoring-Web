<?php

namespace App\Form;

use App\Entity\TrapsSnmp;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class TrapsSnmp1Type extends AbstractType
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
            ->add('signification',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('versionSnmp',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('oid',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('specificTrap',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('variableBinding',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('criticite',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('messageAlarme',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('instructions',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('acquittement',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('mibAssocie',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('objet',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('compelementInformation',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('support',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => TrapsSnmp::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
