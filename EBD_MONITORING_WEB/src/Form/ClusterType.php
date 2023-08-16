<?php

namespace App\Form;

use App\Entity\Cluster;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ClusterType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('nomDuRessourceGroupPackageServiceGuard')
            ->add('adresseIp')
            ->add('listeDesServeursConcernes')
            ->add('logicielCluster')
            ->add('version')
            ->add('mode')
            ->add('serveurActif')
            ->add('complementsInformations')
            ->add('support')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cluster::class,
        ]);
    }
}
