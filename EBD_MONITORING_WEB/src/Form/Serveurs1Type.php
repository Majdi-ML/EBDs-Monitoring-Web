<?php

namespace App\Form;

use App\Entity\Serveurs;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class Serveurs1Type extends AbstractType
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
            ->add('platforme',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('hostname',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('fqdn',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('type',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('modele',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('os',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('verTechFirmware',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('cluster',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('ipSource',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('description',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('socleStandardOmu',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('complementsInformations',TextType::class,[
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
            'data_class' => Serveurs::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
